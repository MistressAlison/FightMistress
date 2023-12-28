package FightMistress.cards;

import FightMistress.cardmods.ComboMod;
import FightMistress.cards.abstracts.AbstractEasyCard;
import FightMistress.powers.ChargedPower;
import FightMistress.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.blue.Aggregate;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FightMistress.MainModfile.makeID;

public class Surge extends AbstractEasyCard {
    public final static String ID = makeID(Surge.class.getSimpleName());

    public Surge() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = 5;
        baseMagicNumber = magicNumber = 2;
        CardModifierManager.addModifier(this, new ComboMod(true));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        Wiz.applyToSelf(new ChargedPower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }

    @Override
    public String cardArtCopy() {
        return Aggregate.ID;
    }
}