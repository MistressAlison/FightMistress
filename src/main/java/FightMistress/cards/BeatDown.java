package FightMistress.cards;

import FightMistress.cardmods.GrabMod;
import FightMistress.cards.abstracts.AbstractEasyCard;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.red.Anger;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FightMistress.MainModfile.makeID;

public class BeatDown extends AbstractEasyCard {
    public final static String ID = makeID(BeatDown.class.getSimpleName());

    public BeatDown() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 10;
        CardModifierManager.addModifier(this, new GrabMod(true));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    @Override
    public void upp() {
        upgradeDamage(3);
        //upgradeBaseCost(1);
    }

    @Override
    public String cardArtCopy() {
        return Anger.ID;
    }
}