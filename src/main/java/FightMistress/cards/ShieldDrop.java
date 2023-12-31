package FightMistress.cards;

import FightMistress.cardmods.GrabMod;
import FightMistress.cards.abstracts.AbstractEasyCard;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.green.Reflex;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FightMistress.MainModfile.makeID;

public class ShieldDrop extends AbstractEasyCard {
    public final static String ID = makeID(ShieldDrop.class.getSimpleName());

    public ShieldDrop() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseBlock = block = 9;
        CardModifierManager.addModifier(this, new GrabMod(true));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void upp() {
        upgradeBlock(3);
        //upgradeBaseCost(1);
    }

    @Override
    public String cardArtCopy() {
        return Reflex.ID;
    }
}