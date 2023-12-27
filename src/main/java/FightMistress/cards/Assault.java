package FightMistress.cards;

import FightMistress.cards.abstracts.AbstractEasyCard;
import FightMistress.cards.tokens.MachPunch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.green.AllOutAttack;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FightMistress.MainModfile.makeID;

public class Assault extends AbstractEasyCard {
    public final static String ID = makeID(Assault.class.getSimpleName());

    public Assault() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 4;
        baseMagicNumber = magicNumber = 2;
        cardsToPreview = new MachPunch();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        addToBot(new MakeTempCardInDrawPileAction(new MachPunch(), magicNumber, true, true));
    }

    @Override
    public void upp() {
        //upgradeDamage(3);
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return AllOutAttack.ID;
    }
}