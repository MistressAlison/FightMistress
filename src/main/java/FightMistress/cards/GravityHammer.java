package FightMistress.cards;

import FightMistress.actions.EasyXCostAction;
import FightMistress.cards.abstracts.AbstractEasyCard;
import FightMistress.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.red.Bludgeon;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

import static FightMistress.MainModfile.makeID;

public class GravityHammer extends AbstractEasyCard {
    public final static String ID = makeID(GravityHammer.class.getSimpleName());

    public GravityHammer() {
        super(ID, -1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 6;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
            Wiz.forAdjacentMonsters(m, mon -> addToBot(new VFXAction(new WeightyImpactEffect(mon.hb.cX, mon.hb.cY))));
        }
        addToBot(new WaitAction(0.8F));
        addToBot(new EasyXCostAction(this, (base, args) -> {
            int multiplier = base;
            for (int i : args) {
                multiplier += i;
            }
            if (m != null) {
                int finalMulti = multiplier;
                Wiz.forAdjacentMonsters(m, mon -> addToTop(new DamageAction(mon, new DamageInfo(p, damage*finalMulti, damageTypeForTurn), AbstractGameAction.AttackEffect.NONE)));
            }
            addToTop(new DamageAction(m, new DamageInfo(p, damage*multiplier, damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
            return true;
        }));

    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public String cardArtCopy() {
        return Bludgeon.ID;
    }
}