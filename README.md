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
