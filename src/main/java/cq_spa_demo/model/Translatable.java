package cq_spa_demo.model;

public class Translatable implements Comparable<Translatable> {
	private final String in;

	private final String out;

	private final String id;

	public Translatable(final String id, final String in, final String out) {
		this.in = in;
		this.out = out;
		this.id = id;
	}

	public String getIn() {
		return this.in;
	}

	public String getId() {
		return this.id;
	}

	public String getOut() {
		return this.out;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		final Translatable other = (Translatable) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s|%s|%s", this.id, this.in, this.out);
	}

	@Override
	public int compareTo(Translatable o) {
		return Integer.compare(Integer.valueOf(this.id), Integer.valueOf(o.id));
	}
}
