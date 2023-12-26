package FightMistress.cards;

import FightMistress.cards.abstracts.AbstractEasyCard;
import FightMistress.damageMods.BootDamage;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.red.Havoc;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FightMistress.MainModfile.makeID;

public class DescentOfDarkness extends AbstractEasyCard {
    public final static String ID = makeID(DescentOfDarkness.class.getSimpleName());
    private static final int BASE = 8;
    private static final int UP = 3;

    public DescentOfDarkness() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = BASE;
        baseSecondMagic = secondMagic = BASE-1;
        baseThirdMagic = thirdMagic = BASE;
        DamageModifierManager.addModifier(this, new BootDamage(BASE));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("ORB_DARK_EVOKE", 0.1F));
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        addToBot(new SFXAction("ORB_DARK_EVOKE", 0.1F));
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    @Override
    public void upp() {
        upgradeDamage(UP);
        upgradeSecondMagic(UP);
        upgradeThirdMagic(UP);
        DamageModifierManager.modifiers(this).forEach(mod -> {
            if (mod instanceof BootDamage) {
                ((BootDamage) mod).amount += UP;
            }
        });
    }

    @Override
    public String cardArtCopy() {
        return Havoc.ID;
    }
}