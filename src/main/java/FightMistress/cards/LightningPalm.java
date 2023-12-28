package FightMistress.cards;

import FightMistress.cards.abstracts.AbstractEasyCard;
import FightMistress.cards.tokens.MachPunch;
import FightMistress.powers.ChargedPower;
import FightMistress.util.Wiz;
import com.megacrit.cardcrawl.cards.blue.BallLightning;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FightMistress.MainModfile.makeID;

public class LightningPalm extends AbstractEasyCard {
    public final static String ID = makeID(LightningPalm.class.getSimpleName());

    public LightningPalm() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        cardsToPreview = new MachPunch();
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new ChargedPower(p, magicNumber));
        Wiz.makeInHand(new MachPunch(), magicNumber);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return BallLightning.ID;
    }
}