package FightMistress.cards;

import FightMistress.actions.DoAction;
import FightMistress.cards.abstracts.AbstractEasyCard;
import FightMistress.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.red.Rampage;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DoubleTapPower;

import static FightMistress.MainModfile.makeID;

public class Unleash extends AbstractEasyCard {
    public final static String ID = makeID(Unleash.class.getSimpleName());

    public Unleash() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = damage = 8;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        addToBot(new DoAction(() -> Wiz.applyToSelf(new DoubleTapPower(p, 1))));
    }

    @Override
    public void upp() {
        upgradeDamage(4);
    }

    @Override
    public String cardArtCopy() {
        return Rampage.ID;
    }
}