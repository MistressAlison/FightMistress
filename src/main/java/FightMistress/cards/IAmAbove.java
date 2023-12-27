package FightMistress.cards;

import FightMistress.cards.abstracts.AbstractEasyCard;
import FightMistress.powers.AbovePower;
import FightMistress.util.Wiz;
import com.megacrit.cardcrawl.cards.red.FeelNoPain;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FightMistress.MainModfile.makeID;

public class IAmAbove extends AbstractEasyCard {
    public final static String ID = makeID(IAmAbove.class.getSimpleName());

    public IAmAbove() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new AbovePower(p));
    }

    @Override
    public void upp() {
        upgradeBaseCost(2);
    }

    @Override
    public String cardArtCopy() {
        return FeelNoPain.ID;
    }
}