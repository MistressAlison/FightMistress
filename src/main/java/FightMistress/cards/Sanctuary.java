package FightMistress.cards;

import FightMistress.cards.abstracts.AbstractEasyCard;
import FightMistress.cards.interfaces.GlowAdjacentCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.optionCards.LiveForever;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FightMistress.MainModfile.makeID;

public class Sanctuary extends AbstractEasyCard implements GlowAdjacentCard {
    public final static String ID = makeID(Sanctuary.class.getSimpleName());
    private static final Color glowAdjacentColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();

    //Passive effect located in DontExhaustOnUsePatches
    public Sanctuary() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = 8;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }

    @Override
    public String cardArtCopy() {
        return LiveForever.ID;
    }

    @Override
    public Color getGlowColor(AbstractCard card) {
        return glowAdjacentColor;
    }
}