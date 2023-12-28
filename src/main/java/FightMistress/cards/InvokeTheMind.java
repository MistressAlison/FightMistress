package FightMistress.cards;

import FightMistress.cards.abstracts.AbstractEasyCard;
import FightMistress.cards.tokens.InvokeTheBody;
import FightMistress.cards.tokens.InvokeTheSpirit;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.purple.EmptyMind;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;

import static FightMistress.MainModfile.makeID;

public class InvokeTheMind extends AbstractEasyCard {
    public final static String ID = makeID(InvokeTheMind.class.getSimpleName());

    public InvokeTheMind() {
        this(true);
    }

    public InvokeTheMind(boolean preview) {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        exhaust = true;
        baseMagicNumber = magicNumber = 2;
        if (preview) {
            MultiCardPreview.add(this, new InvokeTheBody(false), new InvokeTheSpirit(false));
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new InflameEffect(p)));
        addToBot(new DrawCardAction(magicNumber));
        addToBot(new MakeTempCardInDrawPileAction(new InvokeTheBody(), 1, true, true));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return EmptyMind.ID;
    }
}