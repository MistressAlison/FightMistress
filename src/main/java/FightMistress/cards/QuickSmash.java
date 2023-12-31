package FightMistress.cards;

import FightMistress.cardmods.ComboMod;
import FightMistress.cards.abstracts.AbstractEasyCard;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.red.Headbutt;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FightMistress.MainModfile.makeID;

public class QuickSmash extends AbstractEasyCard {
    public final static String ID = makeID(QuickSmash.class.getSimpleName());

    public QuickSmash() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 7;
        baseMagicNumber = magicNumber = 1;
        CardModifierManager.addModifier(this, new ComboMod(true));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        addToBot(new DrawCardAction(magicNumber));
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public String cardArtCopy() {
        return Headbutt.ID;
    }
}