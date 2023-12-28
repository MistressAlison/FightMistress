package FightMistress.cards;

import FightMistress.actions.SheatheCardsAction;
import FightMistress.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.cards.red.InfernalBlade;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FightMistress.MainModfile.makeID;

public class HiddenBlade extends AbstractEasyCard {
    public final static String ID = makeID(HiddenBlade.class.getSimpleName());

    public HiddenBlade() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SheatheCardsAction(p.hand, 1, c -> c.type == CardType.ATTACK));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }

    @Override
    public String cardArtCopy() {
        return InfernalBlade.ID;
    }
}