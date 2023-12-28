package FightMistress.cards.tokens;

import FightMistress.cards.InvokeTheMind;
import FightMistress.cards.abstracts.AbstractEasyCard;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.purple.EmptyFist;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import com.megacrit.cardcrawl.vfx.combat.VerticalImpactEffect;

import static FightMistress.MainModfile.makeID;

public class InvokeTheSpirit extends AbstractEasyCard {
    public final static String ID = makeID(InvokeTheSpirit.class.getSimpleName());

    public InvokeTheSpirit() {
        this(true);
    }

    public InvokeTheSpirit(boolean preview) {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        baseDamage = damage = 30;
        exhaust = true;
        if (preview) {
            MultiCardPreview.add(this, new InvokeTheMind(false), new InvokeTheBody(false));
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new InflameEffect(p)));
        if (m != null) {
            this.addToBot(new VFXAction(new VerticalImpactEffect(m.hb.cX + m.hb.width / 4.0F, m.hb.cY - m.hb.height / 4.0F)));
        }
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    @Override
    public void upp() {
        upgradeDamage(10);
    }

    @Override
    public String cardArtCopy() {
        return EmptyFist.ID;
    }
}