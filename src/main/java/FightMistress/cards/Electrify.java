package FightMistress.cards;

import FightMistress.cards.abstracts.AbstractEasyCard;
import FightMistress.powers.ChargedPower;
import FightMistress.powers.LateLosePowerPower;
import FightMistress.util.Wiz;
import com.megacrit.cardcrawl.cards.blue.StaticDischarge;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;

import static FightMistress.MainModfile.makeID;

public class Electrify extends AbstractEasyCard {
    public final static String ID = makeID(Electrify.class.getSimpleName());

    public Electrify() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new ChargedPower(p, magicNumber));
        Wiz.applyToSelf(new ThornsPower(p, magicNumber));
        Wiz.applyToSelf(new LateLosePowerPower(p, new ThornsPower(p, magicNumber), magicNumber));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }

    @Override
    public String cardArtCopy() {
        return StaticDischarge.ID;
    }
}