package org.lion.actor;

public abstract class SafeActor extends AbstractActor {

    @Override
    protected void loopBody(Message m) {
        try {
            logger.trace("SafeActor loopBody: %s", m);
            doBody((DefaultMessage) m);
        } catch (Exception e) {
            logger.error("SafeActor: exception", e);
        }
    }

    @Override
    protected void runBody() {}

    abstract protected void doBody(DefaultMessage m) throws Exception;
    
}
