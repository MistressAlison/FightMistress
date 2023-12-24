package FightMistress.cards;

import FightMistress.cards.abstracts.AbstractEasyCard;
import FightMistress.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.red.Cleave;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FightMistress.MainModfile.makeID;

public class Sweep extends AbstractEasyCard {
    public final static String ID = makeID(Sweep.class.getSimpleName());

    public Sweep() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = damage = 6;
        isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true);
        Wiz.forAllMonstersLiving(mon -> {
            if (Wiz.isBigger(mon)) {
                addToBot(new DamageAction(mon, new DamageInfo(p, multiDamage[AbstractDungeon.getMonsters().monsters.indexOf(mon)], damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
            }
        });
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m.isDeadOrEscaped() && Wiz.isBigger(m)) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                break;
            }
        }
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public String cardArtCopy() {
        return Cleave.ID;
    }
}