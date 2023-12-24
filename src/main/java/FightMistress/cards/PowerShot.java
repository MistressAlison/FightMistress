package FightMistress.cards;

import FightMistress.actions.DoAction;
import FightMistress.cards.abstracts.AbstractEasyCard;
import FightMistress.util.GameSpeedController;
import FightMistress.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.green.Finisher;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FightMistress.MainModfile.makeID;

public class PowerShot extends AbstractEasyCard {
    public final static String ID = makeID(PowerShot.class.getSimpleName());

    public PowerShot() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 16;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DoAction(() -> GameSpeedController.addSlowMotionEffect(new GameSpeedController.SlowMotionEffect(2, 1/8f))));
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        addToBot(new DoAction(() -> GameSpeedController.addSlowMotionEffect(new GameSpeedController.SlowMotionEffect(16, 1/2f))));

    }

    @Override
    public void upp() {
        upgradeDamage(6);
    }

    @Override
    public boolean freeToPlay() {
        return super.freeToPlay() || (Wiz.isInCombat() && Wiz.getAdjacentCards(this).stream().anyMatch(c -> c.type == CardType.POWER));
    }

    @Override
    public String cardArtCopy() {
        return Finisher.ID;
    }
}