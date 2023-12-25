package FightMistress.cards;

import FightMistress.actions.SheatheCardsAction;
import FightMistress.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.cards.red.SecondWind;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FightMistress.MainModfile.makeID;

public class Iaido extends AbstractEasyCard {
    public final static String ID = makeID(Iaido.class.getSimpleName());

    public Iaido() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseBlock = block = 5;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new SheatheCardsAction(p.hand, p.hand.size(), c -> c.type == CardType.ATTACK));
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }

    @Override
    public String cardArtCopy() {
        return SecondWind.ID;
    }
}