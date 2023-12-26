package FightMistress.cards;

import FightMistress.actions.DoAction;
import FightMistress.cards.abstracts.AbstractEasyCard;
import FightMistress.ui.SheathedCardManager;
import FightMistress.util.Wiz;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.DualWield;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.List;
import java.util.stream.Collectors;

import static FightMistress.MainModfile.makeID;

public class DoubleEdged extends AbstractEasyCard {
    public final static String ID = makeID(DoubleEdged.class.getSimpleName());

    public DoubleEdged() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DoAction(() -> {
            List<AbstractCard> cards = Wiz.cardsPlayedThisCombat().stream().filter(c -> c.type == CardType.ATTACK).collect(Collectors.toList());
            if (!cards.isEmpty()) {
                AbstractCard lastAttack = cards.get(cards.size()-1);
                SheathedCardManager.addCard(lastAttack.makeStatEquivalentCopy());
            }
        }));
    }

    @Override
    public void upp() {
        exhaust = false;
        uDesc();
    }

    @Override
    public String cardArtCopy() {
        return DualWield.ID;
    }

}