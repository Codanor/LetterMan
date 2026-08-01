![logo](LetterMan_Logo.png)

---

# Letter Man

LetterMan is a small Java based Event System. It works on a consumer interface basis with handlers ports and filters.

## Classes

### A_EventConsumer

The abstract base class for everything that receives events. Its <code>consume</code> method is <code>final</code> and silently drops events that have already been consumed, delegating actual work to the abstract <code>p_consume</code> method.

### EventHandler

The main distribution point for events which is itself a consumer. Here you can register any <code>A_EventConsumer</code> implementation.
When calling the <code>consume</code> method of this class, the event is distributed to all registered consumers, after it is optionally filtered.
The filter can be set, changed and retrieved at any time via <code>setFilter</code>/<code>getFilter</code>.

### EventFilter

The event filter is a functional list of <code>Class</code> objects extending <code>A_Event</code> that filters out unwanted events. For that, it can either utilize a black- or whitelist, selected through the <code>FILTER_TYPE</code> enum (<code>WHITELIST</code> or <code>BLACKLIST</code>).
You can then call the <code>filter</code> method that returns a <code>boolean</code> to determine whether it passes, or not.

### A_EventPort

An abstract class, wrapping around the event consumer, keeping track of the event handlers it is subscribed to.
Use <code>subscribe</code> to register with an <code>EventHandler</code> and <code>free</code> to unsubscribe from one or all of them.

### ReactivePort

A concrete port that reacts to every consumed event by invoking a <code>Consumer&lt;A_Event&gt;</code> function, which can be swapped out at any time via <code>setReaction</code>.

### QueuePort

A concrete port that stores every consumed event in an internal FIFO queue, offering <code>peek</code>, <code>poll</code>, <code>clear</code> and <code>contains</code> to inspect or drain it.

### A_Event

The event class is the main information class of LetterMan and can be <code>consumed</code>. Once an event has been consumed, it can not be passed through the consumer interface any longer.
If constructed with <code>throwsConsumeException</code> enabled, consuming an already consumed event throws an <code>AlreadyConsumedException</code>.

### MessageEvent

A simple example event carrying a single immutable message string.
