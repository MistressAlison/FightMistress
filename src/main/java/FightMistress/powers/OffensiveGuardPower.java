package FightMistress.powers;

import FightMistress.MainModfile;
import FightMistress.util.Wiz;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnLoseBlockPower;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class OffensiveGuardPower extends AbstractPower implements OnLoseBlockPower {
    public static final String POWER_ID = MainModfile.makeID(OffensiveGuardPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public OffensiveGuardPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.loadRegion("modeShift");
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public int onLoseBlock(DamageInfo damageInfo, int i) {
        if (owner.currentBlock >= i && damageInfo.type == DamageInfo.DamageType.NORMAL) {
            flash();
            Wiz.applyToSelf(new ChargedPower(Wiz.adp(), amount));
        }
        return i;
    }
}