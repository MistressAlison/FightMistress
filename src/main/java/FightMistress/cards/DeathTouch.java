package FightMistress.cards;

import FightMistress.cardmods.GrabMod;
import FightMistress.cards.abstracts.AbstractEasyCard;
import FightMistress.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.UpgradeSpecificCardAction;
import com.megacrit.cardcrawl.cards.purple.FearNoEvil;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FightMistress.MainModfile.makeID;

public class DeathTouch extends AbstractEasyCard {
    public final static String ID = makeID(DeathTouch.class.getSimpleName());

    public DeathTouch() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        CardModifierManager.addModifier(this, new GrabMod(true));
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded) {
            Wiz.forAdjacentCards(this, c -> {
                if (c.type == CardType.ATTACK) {
                    addToBot(new UpgradeSpecificCardAction(c));
                }
            });
        }
    }

    @Override
    public void upp() {
        uDesc();
    }

    @Override
    public String cardArtCopy() {
        return FearNoEvil.ID;
    }
}