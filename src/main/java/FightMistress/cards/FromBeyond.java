package FightMistress.cards;

import FightMistress.actions.SheatheCardsAction;
import FightMistress.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.cards.red.Exhume;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FightMistress.MainModfile.makeID;

public class FromBeyond extends AbstractEasyCard {
    public final static String ID = makeID(FromBeyond.class.getSimpleName());

    public FromBeyond() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SheatheCardsAction(p.exhaustPile, 1, c -> !(c instanceof FromBeyond)));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }

    @Override
    public String cardArtCopy() {
        return Exhume.ID;
    }
}