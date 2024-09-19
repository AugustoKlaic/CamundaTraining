# Decision Requirements Graph

- Models a domain of decision-making
- Shows the most important elements involved in it and its dependencies
- The elements are: ``decisions``, ``input data`` and ``knowledge sources``
- The visual representation of a **DRG** is called ``Decision Requirements Diagram`` or **DRD**
- DRG Has id and name, the id is the technical identifier, so it should be unique

#### Decisions

- A DRG can have one or more decisions
- Has a name and an id
- The decision logic must be either a decision table or  decision literal expression
- Required decision are the decisions that are in between other decisions, so they are required to another higher decision

#### Input data

- Represents some information that is used as input
- Has no execution semantics
- Is ignored on the evaluation

#### Knowledge source

- Represents an authority for a decision
- Symbolize from where the information will be retrieved (???)
- Is ignored on the evaluation
- Has no execution semantics
