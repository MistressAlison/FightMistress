package FightMistress.cards;

import FightMistress.cardmods.ComboMod;
import FightMistress.cards.abstracts.AbstractEasyCard;
import FightMistress.powers.ExposedPower;
import FightMistress.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.red.Berserk;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.combat.IntimidateEffect;

import static FightMistress.MainModfile.makeID;

public class Frenzy extends AbstractEasyCard {
    public final static String ID = makeID(Frenzy.class.getSimpleName());

    public Frenzy() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 3;
        CardModifierManager.addModifier(this, new ComboMod(true));
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("VO_GREMLINNOB_1B", 0.7f, true));
        addToBot(new VFXAction(p, new IntimidateEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY), 0.1F));
        Wiz.forAllMonstersLiving(mon -> {
            Wiz.applyToEnemy(mon, new VulnerablePower(mon, magicNumber, false));
            Wiz.applyToEnemy(mon, new ExposedPower(mon, magicNumber));
        });
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }

    @Override
    public String cardArtCopy() {
        return Berserk.ID;
    }
}