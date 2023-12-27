package FightMistress.cards;

import FightMistress.cardmods.GrabSmallerMod;
import FightMistress.cards.abstracts.AbstractEasyCard;
import FightMistress.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Intimidate;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ViolentAttackEffect;

import static FightMistress.MainModfile.makeID;

public class Oppressor extends AbstractEasyCard {
    public final static String ID = makeID(Oppressor.class.getSimpleName());

    public Oppressor() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 8;
        CardModifierManager.addModifier(this, new GrabSmallerMod(true));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Wiz.isBigger(m)) {
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        } else {
            if (m != null) {
                addToBot(new VFXAction(new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.VIOLET)));
            }
            dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m.isDeadOrEscaped() && !Wiz.isBigger(m)) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                break;
            }
        }
    }

    /*@Override
    public void calculateCardDamage(AbstractMonster mo) {
        int base = baseDamage;
        if (!Wiz.isBigger(mo)) {
            baseDamage *= 2;
        }
        super.calculateCardDamage(mo);
        baseDamage = base;
        isDamageModified = damage != baseDamage;
    }*/

    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public String cardArtCopy() {
        return Intimidate.ID;
    }
}