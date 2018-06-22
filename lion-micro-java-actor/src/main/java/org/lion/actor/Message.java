package org.lion.actor;

import org.lion.actor.Actor;

public interface Message {

    Actor getSource();

    String getSubject();

    Object getData();

}
