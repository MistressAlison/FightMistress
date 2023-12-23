package FightMistress.cards;

import FightMistress.actions.RearrangeAction;
import FightMistress.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.cards.green.EscapePlan;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FightMistress.MainModfile.makeID;

public class PlanAhead extends AbstractEasyCard {
    public final static String ID = makeID(PlanAhead.class.getSimpleName());

    public PlanAhead() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = 8;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new RearrangeAction());
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }

    @Override
    public String cardArtCopy() {
        return EscapePlan.ID;
    }
}