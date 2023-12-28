package FightMistress.cards;

import FightMistress.cardmods.GrabMod;
import FightMistress.cards.abstracts.AbstractEasyCard;
import FightMistress.powers.ExposedPower;
import FightMistress.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.red.Clothesline;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FightMistress.MainModfile.makeID;

public class Lariat extends AbstractEasyCard {
    public final static String ID = makeID(Lariat.class.getSimpleName());

    public Lariat() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 3;
        CardModifierManager.addModifier(this, new GrabMod(true));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToEnemy(m, new ExposedPower(m, magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }

    @Override
    public String cardArtCopy() {
        return Clothesline.ID;
    }
}