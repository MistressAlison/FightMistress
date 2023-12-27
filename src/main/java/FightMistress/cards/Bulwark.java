package FightMistress.cards;

import FightMistress.cards.abstracts.AbstractEasyCard;
import FightMistress.cards.interfaces.GlowAdjacentCard;
import FightMistress.util.Wiz;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PersistFields;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Barricade;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FightMistress.MainModfile.makeID;

public class Bulwark extends AbstractEasyCard implements GlowAdjacentCard {
    public final static String ID = makeID(Bulwark.class.getSimpleName());
    private static final Color glowAdjacentColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();

    public Bulwark() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        baseBlock = block = 6;
        PersistFields.setBaseValue(this, 1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        if (Wiz.getAdjacentCards(this).size() == 2 && Wiz.getAdjacentCards(this).stream().allMatch(c -> c.type == CardType.ATTACK)) {
            PersistFields.persist.set(this, 2);
        }
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (Wiz.getAdjacentCards(this).size() == 2 && Wiz.getAdjacentCards(this).stream().allMatch(c -> c.type == CardType.ATTACK)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public String cardArtCopy() {
        return Barricade.ID;
    }

    @Override
    public Color getGlowColor(AbstractCard card) {
        return glowAdjacentColor;
    }

    @Override
    public boolean glowAdjacent(AbstractCard card) {
        return GlowAdjacentCard.super.glowAdjacent(card) && card.type == CardType.ATTACK;
    }
}