![logo](LetterMan_Logo.png)

---

# Letter Man

LetterMan is a small Java based Event System. It works on a consumer interface basis with handlers ports and filters.

## Classes

### I_EventConsumer

The general interface introducing the "consume" method, taking in an event.

### EventHandler

The main distribution point for events which is itself a consumer. Here you can register any event consumer implementation.
When calling the "consume" method of this class, the event is distributed to all registered consumers, after it is optionally filtered.

### EventFilter

The event filter is a functional list of subclasses of the event class that filters out unwanted events. For that, it can either utilize a black- or whitelist.
You can then call the <code>filter</code> method that returns a <code>boolean</code> to determine wether it passes, or not.

### A_EventPort

An abstract class, wrapping around the consumer interface, keeping track of the event handlers it is subscribed to.

### A_Event

The event class is the main information class of LetterMan and can be <code>consumed</code>. When an event is consumed, it can not be passed through the consumer interface any longer.
