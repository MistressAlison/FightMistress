package FightMistress.cards;

import FightMistress.actions.DoAction;
import FightMistress.cards.abstracts.AbstractEasyCard;
import FightMistress.ui.SheathedCardManager;
import FightMistress.util.FormatHelper;
import FightMistress.util.Wiz;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.DualWield;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static FightMistress.MainModfile.makeID;

public class DoubleEdged extends AbstractEasyCard {
    public final static String ID = makeID(DoubleEdged.class.getSimpleName());
    private AbstractCard lastCard;

    public DoubleEdged() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DoAction(() -> {
            List<AbstractCard> cards = Wiz.cardsPlayedThisCombat().stream().filter(c -> c.type == CardType.ATTACK).collect(Collectors.toList());
            if (!cards.isEmpty()) {
                AbstractCard lastAttack = cards.get(cards.size()-1);
                SheathedCardManager.addCard(lastAttack.makeStatEquivalentCopy());
            }
        }));
        rawDescription = cardStrings.DESCRIPTION;
        lastCard = null;
        initializeDescription();
    }

    public void triggerOnGlowCheck() {
        if (AbstractDungeon.actionManager.cardsPlayedThisCombat.stream().noneMatch(c -> c.type == CardType.ATTACK)) {
            this.glowColor = Settings.RED_TEXT_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    public void applyPowers() {
        super.applyPowers();
        if (AbstractDungeon.actionManager.cardsPlayedThisCombat.stream().anyMatch(c -> c.type == CardType.ATTACK)) {
            ArrayList<AbstractCard> attacks = AbstractDungeon.actionManager.cardsPlayedThisCombat.stream().filter(c -> c.type == CardType.ATTACK).collect(Collectors.toCollection(ArrayList::new));
            AbstractCard preview = attacks.get(attacks.size()-1);
            if (preview != lastCard) {
                lastCard = preview;
                cardsToPreview = lastCard.makeStatEquivalentCopy();
                this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0] + FormatHelper.prefixWords(lastCard.name, "[#efc851]", "[]") + cardStrings.EXTENDED_DESCRIPTION[1];
                this.initializeDescription();
            }
        }
    }

    @Override
    public void upp() {
        exhaust = false;
        uDesc();
    }

    @Override
    public String cardArtCopy() {
        return DualWield.ID;
    }

}