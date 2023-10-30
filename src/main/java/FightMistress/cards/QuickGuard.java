package FightMistress.cards;

import FightMistress.cards.abstracts.AbstractEasyCard;
import FightMistress.patches.CustomTags;
import FightMistress.util.Wiz;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FightMistress.MainModfile.makeID;

public class QuickGuard extends AbstractEasyCard {
    public final static String ID = makeID(QuickGuard.class.getSimpleName());

    public QuickGuard() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = 6;
        tags.add(CustomTags.MISTRESS_COMBO);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c.type == CardType.ATTACK && Wiz.getAdjacentCards(this).contains(c)) {
            if (m != null) {
                addToBot(new NewQueueCardAction(this, m, true, true));
            } else {
                addToBot(new NewQueueCardAction(this, true, true, true));
            }
        }
    }

    @Override
    public String cardArtCopy() {
        return Defend_Green.ID;
    }
}