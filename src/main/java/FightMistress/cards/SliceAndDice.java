package FightMistress.cards;

import FightMistress.cards.abstracts.AbstractEasyCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.blue.RipAndTear;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.RipAndTearEffect;

import static FightMistress.MainModfile.makeID;

public class SliceAndDice extends AbstractEasyCard {
    public final static String ID = makeID(SliceAndDice.class.getSimpleName());

    public SliceAndDice() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 10;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new RipAndTearEffect(m.hb.cX, m.hb.cY, Color.RED, Color.GOLD)));
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.NONE, true));
        addToBot(new VFXAction(new RipAndTearEffect(m.hb.cX, m.hb.cY, Color.RED, Color.GOLD)));
        addToBot(new DamageAction(m, new DamageInfo(p, damage/2, damageTypeForTurn), AbstractGameAction.AttackEffect.NONE, true));
        addToBot(new VFXAction(new RipAndTearEffect(m.hb.cX, m.hb.cY, Color.RED, Color.GOLD)));
        addToBot(new DamageAction(m, new DamageInfo(p, damage/4, damageTypeForTurn), AbstractGameAction.AttackEffect.NONE, true));
        addToBot(new VFXAction(new RipAndTearEffect(m.hb.cX, m.hb.cY, Color.RED, Color.GOLD)));
        addToBot(new DamageAction(m, new DamageInfo(p, damage/4, damageTypeForTurn), AbstractGameAction.AttackEffect.NONE, true));
    }

    @Override
    public void upp() {
        upgradeDamage(2);
    }

    @Override
    public String cardArtCopy() {
        return RipAndTear.ID;
    }
}