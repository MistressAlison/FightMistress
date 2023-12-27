package FightMistress.cards;

import FightMistress.cards.abstracts.AbstractEasyCard;
import FightMistress.cards.tokens.Voices;
import FightMistress.powers.InTheDarkPower;
import FightMistress.util.Wiz;
import com.megacrit.cardcrawl.cards.red.DarkEmbrace;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FightMistress.MainModfile.makeID;

public class InTheDark extends AbstractEasyCard {
    public final static String ID = makeID(InTheDark.class.getSimpleName());

    public InTheDark() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        cardsToPreview = new Voices();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new InTheDarkPower(p, 1));
    }

    @Override
    public void upp() {
        isInnate = true;
        uDesc();
    }

    @Override
    public String cardArtCopy() {
        return DarkEmbrace.ID;
    }
}