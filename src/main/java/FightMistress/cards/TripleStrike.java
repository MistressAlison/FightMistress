package FightMistress.cards;

import FightMistress.cards.abstracts.AbstractEasyCard;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.red.Pummel;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FightMistress.MainModfile.makeID;

public class TripleStrike extends AbstractEasyCard {
    public final static String ID = makeID(TripleStrike.class.getSimpleName());
    private int lastChecked = -1;

    public TripleStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 2;
        baseMagicNumber = magicNumber = 3;
        tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0 ; i < magicNumber ; i++) {
            dmg(m, i == magicNumber-1 ? AbstractGameAction.AttackEffect.BLUNT_HEAVY : AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        }
    }

    @Override
    public void upp() {
        upgradeDamage(1);
    }

    @Override
    protected void renderTitle(SpriteBatch sb) {
        if (lastChecked != magicNumber) {
            lastChecked = magicNumber;
            changeName();
        }
        super.renderTitle(sb);
    }

    private void changeName() {
        if (magicNumber < 0) {
            this.name = cardStrings.EXTENDED_DESCRIPTION[0];
        } else if (magicNumber > 10) {
            this.name = cardStrings.EXTENDED_DESCRIPTION[10];
        } else {
            this.name = cardStrings.EXTENDED_DESCRIPTION[magicNumber];
        }
        if (timesUpgraded == 1) {
            this.name += "+";
        } else if (timesUpgraded > 1) {
            this.name += "+"+timesUpgraded;
        }
        initializeTitle();
    }

    @Override
    protected void upgradeName() {
        super.upgradeName();
    }

    @Override
    public String cardArtCopy() {
        return Pummel.ID;
    }
}