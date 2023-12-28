package FightMistress.cards;

import FightMistress.cards.abstracts.AbstractEasyCard;
import FightMistress.powers.ChargedPower;
import FightMistress.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.blue.Tempest;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

import static FightMistress.MainModfile.makeID;

public class BoltStrike extends AbstractEasyCard {
    public final static String ID = makeID(BoltStrike.class.getSimpleName());

    public BoltStrike() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = damage = 14;
        baseMagicNumber = magicNumber = 3;
        tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            addToBot(new SFXAction("THUNDERCLAP", 0.05F));
            addToBot(new VFXAction(new LightningEffect(m.drawX, m.drawY), 0.05F));
        }
        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }

    @Override
    public void applyPowers() {
        AbstractPower charged = Wiz.adp().getPower(ChargedPower.POWER_ID);
        int base = 0;
        if (charged != null) {
            base = charged.amount;
            charged.amount *= magicNumber;
        }
        super.applyPowers();
        if (charged != null) {
            charged.amount = base;
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        AbstractPower charged = Wiz.adp().getPower(ChargedPower.POWER_ID);
        int base = 0;
        if (charged != null) {
            base = charged.amount;
            charged.amount *= magicNumber;
        }
        super.calculateCardDamage(mo);
        if (charged != null) {
            charged.amount = base;
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }

    @Override
    public String cardArtCopy() {
        return Tempest.ID;
    }
}