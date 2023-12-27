package FightMistress.powers;

import FightMistress.MainModfile;
import FightMistress.powers.interfaces.CheatCostPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class AbovePower extends AbstractPower implements CheatCostPower {
    public static final String POWER_ID = MainModfile.makeID(AbovePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public AbovePower(AbstractCreature owner) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = -1;
        this.type = PowerType.BUFF;
        this.loadRegion("rupture");
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public boolean canAfford(AbstractCard card) {
        return true;
    }

    @Override
    public void onActivate(AbstractCard card) {
        int delta = card.costForTurn - EnergyPanel.getCurrentEnergy();
        if (delta > 0) {
            flash();
            addToBot(new LoseHPAction(owner, owner, delta, AbstractGameAction.AttackEffect.FIRE));
        }
    }
}