package FightMistress.cards;

import FightMistress.cards.abstracts.AbstractEasyCard;
import FightMistress.powers.CondensePower;
import FightMistress.util.Wiz;
import com.megacrit.cardcrawl.cards.purple.Fasting;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FightMistress.MainModfile.makeID;

public class Condense extends AbstractEasyCard {
    public final static String ID = makeID(Condense.class.getSimpleName());

    public Condense() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new CondensePower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeBaseCost(1);
    }

    @Override
    public String cardArtCopy() {
        return Fasting.ID;
    }
}