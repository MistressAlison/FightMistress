package FightMistress.cards;

import FightMistress.cards.abstracts.AbstractEasyCard;
import FightMistress.powers.SubjugatePower;
import FightMistress.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.red.Disarm;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FightMistress.MainModfile.makeID;

public class Subjugate extends AbstractEasyCard {
    public final static String ID = makeID(Subjugate.class.getSimpleName());

    public Subjugate() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = damage = 10;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToEnemy(m, new SubjugatePower(m, 1));
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
    }

    @Override
    public void upp() {
        upgradeDamage(4);
    }

    @Override
    public String cardArtCopy() {
        return Disarm.ID;
    }
}