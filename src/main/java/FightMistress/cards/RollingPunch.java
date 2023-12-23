package FightMistress.cards;

import FightMistress.actions.DoIfAction;
import FightMistress.cards.abstracts.AbstractEasyCard;
import FightMistress.cards.tokens.DragonPunch;
import FightMistress.util.Wiz;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PersistFields;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.purple.FlurryOfBlows;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FightMistress.MainModfile.makeID;

public class RollingPunch extends AbstractEasyCard {
    public final static String ID = makeID(RollingPunch.class.getSimpleName());

    public RollingPunch() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 6;
        PersistFields.setBaseValue(this, 2);
        cardsToPreview = new DragonPunch();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        addToBot(new DoIfAction(() -> Wiz.cardsPlayedThisTurn().stream().filter(c -> c instanceof RollingPunch).count() >= 2, () -> addToTop(new MakeTempCardInDrawPileAction(new DragonPunch(), 1, true, true))));
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public String cardArtCopy() {
        return FlurryOfBlows.ID;
    }
}