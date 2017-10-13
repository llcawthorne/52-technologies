package com.github.llcawthorne.fitman

import com.twitter.finatra.validation.{Range, Size}
import org.joda.time.Instant

case class Weight(
  @Size(min = 1, max = 25) user: String,
  @Range(min = 50, max = 450) weight: Int,
  status: Option[String],
  postedAt: Instant = Instant.now()
)
