package FightMistress.cards;

import FightMistress.cards.abstracts.AbstractEasyCard;
import FightMistress.cards.tokens.MachPunch;
import FightMistress.powers.AcceleratePlusPower;
import FightMistress.powers.AcceleratePower;
import FightMistress.util.Wiz;
import com.megacrit.cardcrawl.cards.green.Expertise;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FightMistress.MainModfile.makeID;

public class Accelerate extends AbstractEasyCard {
    public final static String ID = makeID(Accelerate.class.getSimpleName());

    public Accelerate() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        cardsToPreview = new MachPunch();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded) {
            Wiz.applyToSelf(new AcceleratePlusPower(p, 1));
        } else {
            Wiz.applyToSelf(new AcceleratePower(p, 1));
        }
    }

    @Override
    public void upp() {
        cardsToPreview.upgrade();
        uDesc();
    }

    @Override
    public String cardArtCopy() {
        return Expertise.ID;
    }
}