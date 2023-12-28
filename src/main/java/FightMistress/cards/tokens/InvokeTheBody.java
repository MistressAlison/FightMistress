package FightMistress.cards.tokens;

import FightMistress.cards.InvokeTheMind;
import FightMistress.cards.abstracts.AbstractEasyCard;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.purple.EmptyBody;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;

import static FightMistress.MainModfile.makeID;

public class InvokeTheBody extends AbstractEasyCard {
    public final static String ID = makeID(InvokeTheBody.class.getSimpleName());

    public InvokeTheBody() {
        this(true);
    }

    public InvokeTheBody(boolean preview) {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE, CardColor.COLORLESS);
        exhaust = true;
        baseMagicNumber = magicNumber = 2;
        if (preview) {
            MultiCardPreview.add(this, new InvokeTheSpirit(false), new InvokeTheMind(false));
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new InflameEffect(p)));
        addToBot(new GainEnergyAction(magicNumber));
        addToBot(new MakeTempCardInDrawPileAction(new InvokeTheSpirit(), 1, true, true));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }

    @Override
    public String cardArtCopy() {
        return EmptyBody.ID;
    }
}