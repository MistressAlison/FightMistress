package FightMistress.cards;

import FightMistress.actions.ApplyCardModifierAction;
import FightMistress.cardmods.ComboMod;
import FightMistress.cards.abstracts.AbstractEasyCard;
import FightMistress.cards.interfaces.GlowAdjacentCard;
import FightMistress.patches.CustomTags;
import FightMistress.util.Wiz;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.SeeingRed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FightMistress.MainModfile.makeID;

public class Truesight extends AbstractEasyCard implements GlowAdjacentCard {
    public final static String ID = makeID(Truesight.class.getSimpleName());
    private static final Color glowAdjacentColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();

    public Truesight() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractCard c : Wiz.getAdjacentCards(this)) {
            addToBot(new ApplyCardModifierAction(c, new ComboMod(false)));
        }
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }

    @Override
    public String cardArtCopy() {
        return SeeingRed.ID;
    }

    @Override
    public Color getGlowColor(AbstractCard card) {
        return glowAdjacentColor;
    }

    @Override
    public boolean glowAdjacent(AbstractCard card) {
        return GlowAdjacentCard.super.glowAdjacent(card) && !card.hasTag(CustomTags.MISTRESS_COMBO);
    }
}