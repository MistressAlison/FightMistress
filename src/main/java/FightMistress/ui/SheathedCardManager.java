package FightMistress.ui;

import FightMistress.actions.InterruptCardAction;
import FightMistress.cards.interfaces.OnSheatheCard;
import FightMistress.patches.CardCounterPatches;
import FightMistress.powers.interfaces.OnSheathePower;
import FightMistress.util.Wiz;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.red.PerfectedStrike;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.OverlayMenu;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.ExhaustPanel;
import com.megacrit.cardcrawl.vfx.BobEffect;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;

import java.util.ArrayList;

public class SheathedCardManager {
    public static final float Y_OFFSET = 70f * Settings.scale;
    public static final float X_OFFSET = 100f * Settings.scale;
    public static final CardGroup cards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
    public static final CardGroup renderQueue = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
    private static final ArrayList<AbstractGameAction> capturedActions = new ArrayList<>();
    private static final BobEffect bob = new BobEffect(3.0f * Settings.scale, 3.0f);
    public static AbstractCard hovered;

    public static void render(SpriteBatch sb) {
        for (AbstractCard card : cards.group) {
            if (card != hovered) {
                card.render(sb);
            }
        }
        if (hovered != null) {
            hovered.render(sb);
            TipHelper.renderTipForCard(hovered, sb, hovered.keywords);
        }
        renderQueue.render(sb);
    }

    public static void update() {
        bob.update();
        int i = 0;
        hovered = null;
        for (AbstractCard card : cards.group) {
            card.target_y = Wiz.adp().hb.cY + Wiz.adp().hb.height/2f + Y_OFFSET + bob.y;
            card.target_x = Wiz.adp().hb.cX + X_OFFSET * (cards.size()-1) / 2f - X_OFFSET * i;
            card.targetAngle = 0f;
            card.update();
            card.hb.update();
            if (card.hb.hovered && hovered == null) {
                card.targetDrawScale = 0.75f;
                hovered = card;
            } else {
                card.targetDrawScale = 0.2f;
            }
            card.applyPowers();
            i++;
        }
        renderQueue.update();
    }

    public static void playCards() {
        for (AbstractCard card : cards.group) {
            card.targetDrawScale = 0.75F;
            card.applyPowers();
            SheathedCardFields.sheathedField.set(card, true);
        }
        renderQueue.group.addAll(cards.group);
        cards.clear();
        playNextCard();
    }

    public static void playNextCard() {
        if (!renderQueue.isEmpty()) {
            AbstractCard card = renderQueue.group.get(0);
            if (!SheathedCardFields.interruptedField.get(card)) {
                Wiz.atb(new NewQueueCardAction(card, true, false, true));
            } else {
                Wiz.atb(new InterruptCardAction(card));
            }
        }
    }

    public static void playNextCard(DamageInfo info) {
        if (!Wiz.adp().isDead && info.owner != Wiz.adp()) {
            //SheathedCardManager.playCards();
            if (!cards.isEmpty()) {
                AbstractCard card = cards.group.get(0);
                cards.removeCard(card);
                renderQueue.addToTop(card);
                capturedActions.addAll(AbstractDungeon.actionManager.actions);
                AbstractDungeon.actionManager.actions.clear();
                card.targetDrawScale = 0.75F;
                card.applyPowers();
                SheathedCardFields.sheathedField.set(card, true);
                Wiz.att(new NewQueueCardAction(card, info.owner, true, true));
            }
        }
    }

    public static void addCard(AbstractCard card) {
        addCard(card, true, false);
    }

    public static void addCard(AbstractCard card, boolean playSFX) {
        addCard(card, playSFX, false);
    }

    public static void addCard(AbstractCard card, boolean playSFX, boolean isEndTurn) {
        card.targetAngle = 0f;
        card.beginGlowing();
        cards.addToTop(card);
        if (card instanceof OnSheatheCard) {
            ((OnSheatheCard) card).onSheathe();
        }
        for (AbstractPower p : Wiz.adp().powers) {
            if (p instanceof OnSheathePower) {
                ((OnSheathePower) p).onSheathe(card, isEndTurn);
            }
        }
        if (playSFX) {
            CardCrawlGame.sound.play("ORB_SLOT_GAIN", 0.1F);
        }
        CardCounterPatches.cardsSheathedThisTurn++;
        CardCounterPatches.cardsSheathedThisCombat++;
    }

    @SpirePatch2(clz = AbstractCard.class, method = SpirePatch.CLASS)
    public static class SheathedCardFields {
        public static SpireField<Boolean> sheathedField = new SpireField<>(() -> false);
        public static SpireField<Boolean> interruptedField = new SpireField<>(() -> false);
    }

    @SpirePatch2(clz = UseCardAction.class, method = SpirePatch.CLASS)
    public static class SheathedActionField {
        public static SpireField<Boolean> sheathedField = new SpireField<>(() -> false);
    }

    @SpirePatch2(clz = UseCardAction.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {AbstractCard.class, AbstractCreature.class})
    public static class InheritProjectedField {
        @SpirePrefixPatch
        public static void pushProjected(UseCardAction __instance, AbstractCard card) {
            if (SheathedCardFields.sheathedField.get(card)) {
                SheathedActionField.sheathedField.set(__instance, true);
                SheathedCardFields.sheathedField.set(card, false);
                SheathedCardFields.interruptedField.set(card, false);
            }
        }
    }

    public static boolean checkCard(AbstractCard card, boolean turnHasEnded) {
        return turnHasEnded && !SheathedCardFields.sheathedField.get(card);
    }

    @SpirePatch2(clz = AbstractCard.class, method = "hasEnoughEnergy")
    public static class CanPlayOffTurn {
        @SpireInstrumentPatch
        public static ExprEditor plz() {
            return new ExprEditor() {
                @Override
                public void edit(FieldAccess f) throws CannotCompileException {
                    if (f.getFieldName().equals("turnHasEnded") && f.getClassName().equals(GameActionManager.class.getName())) {
                        f.replace("$_="+SheathedCardManager.class.getName()+".checkCard(this, $proceed($$));");
                    }
                }
            };
        }
    }

    @SpirePatch2(clz = UseCardAction.class, method = "update")
    public static class DoNextCard {
        @SpirePostfixPatch
        public static void doNextProjectedCard(AbstractCard ___targetCard) {
            SheathedCardManager.renderQueue.removeCard(___targetCard);
            for (AbstractGameAction a : capturedActions) {
                Wiz.atb(a);
            }
            capturedActions.clear();
            //playNextCard();
        }
    }

    @SpirePatch2(clz = OverlayMenu.class, method = "render")
    public static class RenderPanel {
        @SpireInsertPatch(locator = Locator.class)
        public static void render(OverlayMenu __instance, SpriteBatch sb) {
            SheathedCardManager.render(sb);
        }

        public static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher m = new Matcher.MethodCallMatcher(ExhaustPanel.class, "render");
                return LineFinder.findInOrder(ctBehavior, m);
            }
        }
    }

    @SpirePatch2(clz = AbstractPlayer.class, method = "combatUpdate")
    public static class UpdatePile {
        @SpirePostfixPatch
        public static void update(AbstractPlayer __instance) {
            SheathedCardManager.update();
        }
    }

    @SpirePatch2(clz = AbstractPlayer.class, method = "preBattlePrep")
    @SpirePatch2(clz = AbstractPlayer.class, method = "onVictory")
    public static class EmptyCards {
        @SpirePostfixPatch
        public static void yeet() {
            cards.clear();
            renderQueue.clear();
        }
    }

    @SpirePatch2(clz = GameActionManager.class, method = "getNextAction")
    public static class DontYeetProjectedCards {
        @SpireInsertPatch(locator = Locator.class)
        public static SpireReturn<?> addCardsBack(GameActionManager __instance) {
            AbstractCard c = __instance.cardQueue.get(0).card;
            if (c != null && SheathedCardFields.sheathedField.get(c)) {
                Wiz.atb(new InterruptCardAction(c));
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }

        public static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher m = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "limbo");
                return LineFinder.findAllInOrder(ctBehavior, m);
            }
        }
    }

    @SpirePatch2(clz = GameActionManager.class, method = "callEndTurnEarlySequence")
    public static class InterruptCardsPlz {
        @SpirePostfixPatch
        public static void setField() {
            for (AbstractCard card : SheathedCardManager.renderQueue.group) {
                SheathedCardFields.interruptedField.set(card, true);
            }
        }
    }

    @SpirePatch2(clz = PerfectedStrike.class, method = "countCards")
    public static class CountProjectedCardsPlz {
        @SpirePostfixPatch
        public static int getCount(int __result) {
            int projectedStrikes = (int)(SheathedCardManager.cards.group.stream().filter(c -> c.hasTag(AbstractCard.CardTags.STRIKE)).count() + SheathedCardManager.renderQueue.group.stream().filter(c -> c.hasTag(AbstractCard.CardTags.STRIKE)).count());
            return __result + projectedStrikes;
        }
    }
}
