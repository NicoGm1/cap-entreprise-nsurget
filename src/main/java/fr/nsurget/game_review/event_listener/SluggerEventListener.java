package fr.nsurget.game_review.event_listener;


import fr.nsurget.game_review.entity.interfaces.SluggerInterface;
import fr.nsurget.game_review.utils.Slugger;
import lombok.AllArgsConstructor;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SluggerEventListener implements PreInsertEventListener,
        PreUpdateEventListener {

    private Slugger slugger;

    @Override
    public boolean onPreInsert(PreInsertEvent preInsertEvent) {
        if (preInsertEvent.getEntity() instanceof SluggerInterface) {
            return hasSlugify((SluggerInterface) preInsertEvent.getEntity());
        }
        return false;
    }

    @Override
    public boolean onPreUpdate(PreUpdateEvent preUpdateEvent) {
        if (preUpdateEvent.getEntity() instanceof SluggerInterface) {
            return hasSlugify((SluggerInterface) preUpdateEvent.getEntity());
        }
        return false;
    }

    private boolean hasSlugify(SluggerInterface o) {
        if (o.getField() == null) {
            System.out.println(".getField de " + o.getClass().getSimpleName() + " return null");
            return true;
        }
        o.setSlug(slugger.slugify(o.getField()));
        return false;
    }

}
