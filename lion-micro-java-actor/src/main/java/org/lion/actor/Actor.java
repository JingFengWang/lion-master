package org.lion.actor;

public interface Actor extends Runnable {

    String DEFAULT_CATEGORY = "default";

    String getName();

    void setName(String name);

    String getCategory();

    void setCategory(String category);

    boolean receive();

    boolean willReceive(String subject);

    Message peekNext();

    Message peekNext(String subject);

    Message peekNext(String subject, boolean isRegExpr);

    boolean remove(Message message);

    void activate();

    void deactivate();

    void setSuspended(boolean f);

    boolean isSuspended();

    void shutdown();

    boolean isShutdown();

    int getMessageCount();

    int getMaxMessageCount();

}
