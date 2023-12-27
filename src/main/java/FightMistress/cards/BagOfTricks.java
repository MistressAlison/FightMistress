package FightMistress.cards;

import FightMistress.actions.GatherAction;
import FightMistress.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.cards.green.Prepared;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FightMistress.MainModfile.makeID;

public class BagOfTricks extends AbstractEasyCard {
    public final static String ID = makeID(BagOfTricks.class.getSimpleName());

    public BagOfTricks() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GatherAction(3, c -> c.type == CardType.ATTACK, upgraded, false));
    }

    @Override
    public void upp() {
        uDesc();
    }

    @Override
    public String cardArtCopy() {
        return Prepared.ID;
    }

}