package FightMistress.powers;

import FightMistress.MainModfile;
import FightMistress.ui.SheathedCardManager;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ParryPower extends AbstractPower {
    public static final String POWER_ID = MainModfile.makeID(ParryPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ParryPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.loadRegion("noPain");
        updateDescription();
    }

    public void atEndOfTurn(boolean playerTurn) {
        if (!SheathedCardManager.cards.isEmpty()) {
            flash();
            addToBot(new GainBlockAction(owner, SheathedCardManager.cards.size()*amount));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}