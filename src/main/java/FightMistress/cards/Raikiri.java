package FightMistress.cards;

import FightMistress.actions.SheatheCardsAction;
import FightMistress.cards.abstracts.AbstractEasyCard;
import FightMistress.powers.ChargedPower;
import FightMistress.util.Wiz;
import com.megacrit.cardcrawl.cards.blue.ThunderStrike;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FightMistress.MainModfile.makeID;

public class Raikiri extends AbstractEasyCard {
    public final static String ID = makeID(Raikiri.class.getSimpleName());

    public Raikiri() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new ChargedPower(p, magicNumber));
        addToBot(new SheatheCardsAction(p.hand, 1, Wiz.getAdjacentCards(this)::contains));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return ThunderStrike.ID;
    }
}