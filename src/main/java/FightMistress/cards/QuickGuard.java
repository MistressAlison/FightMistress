package FightMistress.cards;

import FightMistress.cardmods.ComboMod;
import FightMistress.cards.abstracts.AbstractEasyCard;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FightMistress.MainModfile.makeID;

public class QuickGuard extends AbstractEasyCard {
    public final static String ID = makeID(QuickGuard.class.getSimpleName());

    public QuickGuard() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = 5;
        CardModifierManager.addModifier(this, new ComboMod(true));
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
    public String cardArtCopy() {
        return Defend_Green.ID;
    }
}