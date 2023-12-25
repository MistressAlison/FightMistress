package FightMistress.cards;

import FightMistress.actions.SheatheCardsAction;
import FightMistress.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.green.CloakAndDagger;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FightMistress.MainModfile.makeID;

public class Subterfuge extends AbstractEasyCard {
    public final static String ID = makeID(Subterfuge.class.getSimpleName());

    public Subterfuge() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 12;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        addToBot(new SheatheCardsAction(p.hand, 1));
    }

    @Override
    public void upp() {
        upgradeDamage(4);
    }

    @Override
    public String cardArtCopy() {
        return CloakAndDagger.ID;
    }
}