package FightMistress.cards;

import FightMistress.actions.DamageFollowupAction;
import FightMistress.cards.abstracts.AbstractEasyCard;
import FightMistress.powers.ExposedPower;
import FightMistress.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.purple.CrushJoints;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FightMistress.MainModfile.makeID;

public class Shatter extends AbstractEasyCard {
    public final static String ID = makeID(Shatter.class.getSimpleName());

    public Shatter() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = damage = 5;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageFollowupAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY, false, mon -> {
            if (mon.lastDamageTaken > 0 && mon instanceof AbstractMonster) {
                Wiz.applyToEnemyTop((AbstractMonster) mon, new ExposedPower(mon, mon.lastDamageTaken));
            }
        }));
    }

    @Override
    public void upp() {
        upgradeDamage(2);
    }

    @Override
    public String cardArtCopy() {
        return CrushJoints.ID;
    }
}